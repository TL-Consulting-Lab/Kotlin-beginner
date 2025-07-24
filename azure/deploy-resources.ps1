# Deploy Azure Resources

# Set variables
$resourceGroupName = "kotlin-app-rg"
$location = "East US"
$appName = "kotlin-product-management"

Write-Host " Starting Azure deployment..." -ForegroundColor Green

# Check if logged in to Azure
try {
    $account = az account show 2>$null
    if ($LASTEXITCODE -ne 0) {
        Write-Host " Please log in to Azure:" -ForegroundColor Yellow
        Write-Host "Run: az login" -ForegroundColor Cyan
        Write-Host "Then run this script again." -ForegroundColor Yellow
        exit 1
    }
    Write-Host " Already logged in to Azure" -ForegroundColor Green
} catch {
    Write-Host " Please log in to Azure first with: az login" -ForegroundColor Yellow
    exit 1
}

# Create resource group
Write-Host "Creating resource group: $resourceGroupName" -ForegroundColor Cyan
az group create --name $resourceGroupName --location $location

# Deploy ARM template
Write-Host "Deploying Azure resources..." -ForegroundColor Cyan
az deployment group create --resource-group $resourceGroupName --template-file azure-resources.json --parameters appName=$appName

Write-Host " Basic deployment complete!" -ForegroundColor Green
