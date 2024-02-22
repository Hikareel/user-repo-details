# About project
___
If given proper GitHub username it returns names of
user repositories, names of branches and SHA of last commit in that branch.

### Accepted Request Body
After program run we can use for example Postman to send GET request
with Key: "username" and Value "GH_NAME", where GH_NAME is name of user
u want to get data of.

Endpoint for this response is http://localhost:8080/repos

### Returned Value
When given existing GH_NAME response format is:
<pre>
<code>
{
    "Owner Login": "GH_NAME",
    "Repos": [
        {
            "Repository Name": "GH_REPO_NAME",
            "Branches": [
                {
                    "Branch Name": "GH_BRANCH_NAME",
                    "SHA": "GH_LAST_COMMIT_SHA"
                }
            ]
        }
    ]
}
</code> 
</pre>

When given not existing GH_NAME response format is:
<pre>
<code>
{
    "status": "ERROR_STATUS_CODE",
    "message": "ERROR_MESSAGE"
}
</code> 
</pre>