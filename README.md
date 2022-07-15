# testApp
Endpoints for testing in Postman
    Columns:

Get all columns -  GET"/"

Get columns by id - GET"/id"

Create column - POST "/"
                {
                    "name": "new column name"
                }

Update column by id - PUT "/id"
                {
                    "name": "updated column name"
                }

Delete column by id - DELETE "/id"

    Tasks:
Get all tasks -  GET"/tasks"

Create task - POST "/tasks?columnId=id"
                {
                    "name": "new task name",
                    "description": "new description"
                }

Update task - PUT "/tasks/id?columnId=id"
                {
                    "name": "edited task name",
                    "description": "edited description"
                }

Delete task by id - DELETE "/tasks/id"

