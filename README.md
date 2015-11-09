## Run the application
```shell
$ activator
> run
$ open http://localhost:9000
```

## Heroku

1. `$ heroku apps:create; heroku apps:info` creates an app and prints its name.
2. Set the `herokuAppName` in `build.sbt` with the name of the application you created.
3. `$ activator stage deployHeroku`
