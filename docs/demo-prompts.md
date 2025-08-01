# Demo Prompts for Kotlin Application
This file contains the prompts used during the development and testing of a Kotlin application which does product management.

## Explain Code
Inline Copilot chat
Hiighlight a block of code and ask copilot using /Explain

Agent Mode
- Please explain this codebase and provide a high-level summary of its structure and purpose

## Using Inline comments for code generation
Go to, ProductManager.kt, write // Update an existing product. 
Copilot will analyze and suggest a code block, You can accept by clicking enter 

## Add functionality
Add task management functionality 
- add a new feature to Update/Edit Products, allowing users to modify product details

Update main.kt and frontend as well along with any other dependencies
Suggest commands to run this

Compile the updated code
> cd Kotlin-beginner
> cmd /c "gradlew.bat build -x test"

Run the application
> java -jar ".\build\libs\product-management-system-1.0.0.jar"

Open Web Interface
> cd frontend
> start index.html

Additional features
- add a new feature to Delete Products, allowing users to remove products from the system
- add a new feature for product Status: Active/Inactive/Discontinued status
- add a new feature to the application to support image upload and display for products.
- add an option to add to cart and view cart items

Ask questions on what the feature does, how it should work, and what the expected behavior is.

## Test Implementation

- Write unit for test the create product functionality
- Implement tests for:
    - Frontend components 
    - Backend API endpoints
    - Error handling scenarios
    - State management
 
## Refactoring code
Refactor this code so that it follows the builder design pattern

## Documentation

- Provide setup instructions for developers new to Kotlin
- Generate Code comments
- Generate API documentation
- Generate Setup instructions
- Generate Development notes

## Workflow Diagram

- /create workflow diagram of end-to-end feature flow (from adding, searching to viewing products)

To view it as a diagram:
- Installing extension `Markdown Preview Mermaid Support'
- Open WORKFLOW.md in VS Code
- Press Ctrl+Shift+V (Windows) to open the preview
- The Mermaid diagrams will be rendered automatically

## Deploy to Azure
Use github actions to deploy the kotlin application to azure


## Check if servers are running
- check if both servers are running correctly

## perform an action
- run the app

## Setup, Run & Health Check

- Setup the project and verify it works.

