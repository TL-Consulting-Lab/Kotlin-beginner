# Azure Deployment Guide for Kotlin Product Management System

This guide explains how to deploy the Kotlin Product Management System to Azure using GitHub Actions.

## Overview

The project includes three different deployment strategies:

1. **Azure App Service** - For the complete application (JAR + Frontend)
2. **Azure Static Web Apps** - For the frontend only
3. **Azure Container Instances** - Using Docker containers (Recommended)

## Prerequisites

Before deploying, you need to set up the following in Azure and GitHub:

### Azure Resources

1. **Azure Container Registry (ACR)** (for Docker deployment)
2. **Azure Container Instances** or **Azure App Service**
3. **Azure Static Web Apps** (for frontend-only deployment)
4. **Resource Group** to contain all resources

### GitHub Secrets

Add the following secrets to your GitHub repository (`Settings > Secrets and variables > Actions`):

#### For App Service Deployment:
- `AZURE_WEBAPP_PUBLISH_PROFILE` - Download from Azure App Service

#### For Container Deployment:
- `AZURE_CREDENTIALS` - Service principal credentials
- `AZURE_ACR_USERNAME` - ACR username
- `AZURE_ACR_PASSWORD` - ACR password

#### For Static Web Apps:
- `AZURE_STATIC_WEB_APPS_API_TOKEN` - API token from Azure Static Web Apps

## Deployment Options

### Option 1: Azure Container Instances (Recommended)

This method uses Docker to containerize your application and deploy it to Azure Container Instances.

**Advantages:**
- Consistent environment
- Easy scaling
- Includes both backend and frontend
- Better resource management

**Setup Steps:**

1. Create Azure Container Registry:
```bash
az acr create --resource-group kotlin-app-rg --name kotlinappregistry --sku Basic --admin-enabled true
```

2. Get ACR credentials:
```bash
az acr credential show --name kotlinappregistry
```

3. Create service principal:
```bash
az ad sp create-for-rbac --name "kotlin-app-sp" --role contributor --scopes /subscriptions/YOUR_SUBSCRIPTION_ID/resourceGroups/kotlin-app-rg --sdk-auth
```

4. Configure GitHub secrets with the credentials from steps 2 and 3.

5. Update the workflow file `.github/workflows/deploy-to-azure-container.yml` with your resource names.

6. Push to main branch to trigger deployment.

### Option 2: Azure App Service

Deploy the JAR file and frontend to Azure App Service.

**Setup Steps:**

1. Create Azure App Service with Java runtime
2. Download the publish profile from Azure portal
3. Add `AZURE_WEBAPP_PUBLISH_PROFILE` secret to GitHub
4. Update `AZURE_WEBAPP_NAME` in `.github/workflows/deploy-to-azure.yml`
5. Push to main branch to trigger deployment

### Option 3: Azure Static Web Apps (Frontend Only)

Deploy only the frontend to Azure Static Web Apps.

**Setup Steps:**

1. Create Azure Static Web Apps resource
2. Get the API token from the resource
3. Add `AZURE_STATIC_WEB_APPS_API_TOKEN` secret to GitHub
4. Push changes to frontend folder to trigger deployment

## Configuration

### Environment Variables

Update the following variables in the workflow files:

#### For Container Deployment:
```yaml
env:
  AZURE_CONTAINER_REGISTRY: kotlinappregistry    # Your ACR name
  CONTAINER_NAME: kotlin-product-management
  RESOURCE_GROUP: kotlin-app-rg                  # Your resource group
  AZURE_CONTAINER_INSTANCE: kotlin-app-instance
```

#### For App Service Deployment:
```yaml
env:
  AZURE_WEBAPP_NAME: kotlin-product-management    # Your app service name
  JAVA_VERSION: '11'
```

## Application Structure

The deployment includes:

- **Kotlin Backend**: Compiled to `ProductManagementSystem.jar`
- **Frontend**: HTML, CSS, and JavaScript files
- **Docker Container**: Runs both backend and serves frontend

## Ports and URLs

- **Backend**: Kotlin console application (no web server by default)
- **Frontend**: Served on port 8080 in Docker container
- **Access URL**: `http://your-container-instance-fqdn:8080`

## Monitoring and Logs

### Azure Container Instances
```bash
# View container logs
az container logs --resource-group kotlin-app-rg --name kotlin-app-instance

# Check container status
az container show --resource-group kotlin-app-rg --name kotlin-app-instance
```

### Azure App Service
- View logs in Azure portal under "Log stream"
- Use Application Insights for detailed monitoring

## Troubleshooting

### Common Issues:

1. **Build Failures**: Ensure Kotlin source files are valid
2. **Container Start Issues**: Check Docker logs for Java/Kotlin errors
3. **Port Issues**: Verify port 8080 is properly exposed
4. **ACR Authentication**: Ensure ACR credentials are correct

### Debug Steps:

1. Check GitHub Actions logs
2. Verify Azure resource configuration
3. Test Docker image locally:
```bash
docker build -t kotlin-app .
docker run -p 8080:8080 kotlin-app
```

## Security Considerations

- Use Azure Key Vault for sensitive configuration
- Enable Azure AD authentication for production
- Configure network security groups
- Use managed identities instead of service principals when possible

## Cost Optimization

- Use Azure Container Instances for development/testing
- Consider Azure Kubernetes Service (AKS) for production
- Implement auto-scaling based on demand
- Use Azure Reserved Instances for consistent workloads

## Next Steps

1. Set up monitoring and alerting
2. Implement CI/CD pipelines for different environments
3. Add automated testing
4. Configure custom domain names
5. Implement SSL/TLS certificates
6. Set up backup and disaster recovery

## Support

For issues with:
- **Kotlin compilation**: Check Kotlin version compatibility
- **Azure deployment**: Review Azure documentation
- **GitHub Actions**: Check workflow syntax and secrets

---

**Note**: Remember to update all placeholder values (resource names, subscription IDs, etc.) with your actual Azure resource names before deploying.
