# lambda-sendgrid-slack

A lambda function to post sendgrid events to slack.

## Usage

### Get a slack api token
* Get a slack api token [here](https://api.slack.com/custom-integrations/legacy-tokens).

### Generate uberjar
* Clone the repo
* Run `lein uberjar` and get the standalone jar.

### Lambda function
* Create lambda function
* Upload standalone jar.
* Set runtime to `Java 8`
* Set handler to `lambda-sendgrid-slack.core.handler`
* Set the `SLACK_API_TOKEN` environment variable. (optionaly set the other variables)

### Options

| Var name                | Default                 | Description |
| ----------------------- | ----------------------- | ------------------------------------------ |
| SLACK_API_URL           | `https://slack.com/api` | Slack api url. Can be changed for testing. |
| SLACK_API_TOKEN         |                         | Your api token. Np default set.            |
| SLACK_CHANNEL           | `#general`              | Channel to post to.                        |
| SLACK_SENDGRID_USERNAME | `Sendgrid`              | Username to display in channel.            |
| SLACK_ICON_EMOJI        | `:email:`               | Icon to use in channel.                    |


### Create API Gateway proxy.

Follow [these](https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-create-api-as-simple-proxy-for-lambda.html#api-gateway-create-api-as-simple-proxy-for-lambda-build) instructions to set up and deploy the api. Optionally, add it to an existing api.

### Add sendgrid webhook.

Set up the Sendgrid Webhook ([instructions](https://sendgrid.com/docs/API_Reference/Webhooks/event.html)) to point to your newly created api.


Copyright © 2017 Manuel Zapata
