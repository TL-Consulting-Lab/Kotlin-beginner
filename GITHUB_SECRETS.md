# GitHub Secrets Configuration

## Add these secrets to your GitHub repository (Settings > Secrets and variables > Actions):

### 1. AZURE_ACR_USERNAME
```
kotlinappregistry1859
```

### 2. AZURE_ACR_PASSWORD  
```
jJpsiwgOznk14ejbrmkFkOG7QNjHTmmbHw3vVSTbrC+ACRDEdqYn
```

### 3. AZURE_CREDENTIALS (Complete JSON)
```json
{
  "clientId": "d2eb5f87-ef5e-4369-ad5c-897beb9f2281",
  "clientSecret": "~WS8Q~ReCWKO5_ygY118oLFKKeanFbH7X5reGcEW",
  "subscriptionId": "5c995f79-6f18-411a-9f9c-b620cae67a7e",
  "tenantId": "b3160369-f77c-4a54-a99c-2c0a4e0f5c89",
  "activeDirectoryEndpointUrl": "https://login.microsoftonline.com",
  "resourceManagerEndpointUrl": "https://management.azure.com/",
  "activeDirectoryGraphResourceId": "https://graph.windows.net/",
  "sqlManagementEndpointUrl": "https://management.core.windows.net:8443/",
  "galleryEndpointUrl": "https://gallery.azure.com/",
  "managementEndpointUrl": "https://management.core.windows.net/"
}
```

## Resource Information Created:
- **Resource Group**: kotlin-app-rg
- **Container Registry**: kotlinappregistry1859.azurecr.io
- **Location**: eastus

## Next Steps:
1. Add the above secrets to GitHub
2. Update the workflow files with the correct resource names
3. Push your code to trigger deployment
