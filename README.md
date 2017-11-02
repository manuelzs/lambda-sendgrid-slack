# lambda-sendgrid-slack

A lambda function to post sendgrid events to slack.

## Usage

* Get a slack api token [here](https://api.slack.com/custom-integrations/legacy-tokens).
* Generate uberjar `lein uberjar`.
* Create lambda function and upload jar.
* Create API Gateway proxy.
* Add sendgrid webhook.

### Options

| Var name                | Default                 | Description |
| ----------------------- | ----------------------- | ------------------------------------------ |
| SLACK_API_URL           | `https://slack.com/api` | Slack api url. Can be changed for testing. |
| SLACK_API_TOKEN         |                         | Your api token. Np default set.            |
| SLACK_CHANNEL           | `#general`              | Channel to post to.                        |
| SLACK_SENDGRID_USERNAME | `Sendgrid`              | Username to display in channel.            |
| SLACK_ICON_EMOJI        | `:email:`               | Icon to use in channel.                    |


Copyright © 2017 Manuel Zapata
