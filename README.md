# Embulk: plugin-based parallel bulk data loader

## What's Embulk?

TODO

## Quick Start

The single-file package is the simplest way to try Embulk. You can download the latest embulk.jar from [releases]() and run it with java:

```
wget https://github.com/embulk/embulk/releases .... /latest
java -jar embulk.jar --help
```

Let's load a CSV file, for example. `embulk bundle` subcommand generates a CSV file for you.

```
java -jar embulk.jar bundle ./mydata   # create a new bundle directory
java -jar embulk.jar guess  ./mydata/examples/csv-stdout.yml -o example.yml
java -jar embulk.jar preview example.yml
java -jar embulk.jar run     example.yml
```

### Using plugins

You can use plugins to load data from/to various systems and file formats.
An example is [embulk-output-postgres-json]() plugin. It outputs data into PostgreSQL server using "json" column type.

```
java -jar embulk.jar gem install embulk-output-postgres-json
java -jar embulk.jar gem list
```

You can search plugins on RubyGems: [search for "embulk-"](https://rubygems.org/search?utf8=%E2%9C%93&query=embulk-).

### Using plugin bundle

`embulk bundle` subcommand generates some example plugins at $bundle/embulk/\*.rb directory.

```
sed -i .orig s/stdout/example/ ./mydata/examples/csv-stdout.yml
java -jar embulk.jar guess  -b ./mydata ./mydata/examples/csv-stdout.yml -o example.yml
java -jar embulk.jar run    -b ./mydata example.yml
```

See generated ./mydata/Gemfile how to create and use plugin bundles.

### Releasing plugins to RubyGems

TODO: documents

```
embulk-plugin-xyz
```

## Embulk Development

### Build

```
rake
java -jar embulk.jar guess ./lib/embulk/data/bundle/examples/csv-stdout.yml > config.yml
java -jar embulk.jar preview config.yml
java -jar embulk.jar run config.yml
```

You can see JaCoCo's test coverage report at ${project}/target/site/jacoco/index.html

