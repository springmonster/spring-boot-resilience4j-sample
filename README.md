# Spring-Resilience4j-Demo

# Getting Started

Start server
Application is running on http://localhost:8082

# Monitoring with Prometheus and Grafana（Optional）

## Requirements

- Windows: install `Docker Desktop`
- Linux or MacOS: install `docker` and `docker-compose`

## Step 1

Build docker image

- First generate jar in `/build/libs` folder
- In the root folder

```
docker build -t spring-resilience4j-demo .
```

## Step 2

Use docker-compose to start Grafana and Prometheus servers.

- In the root folder

```
docker-compose -f docker-compose-prometheus-grafana.yml up -d
```

## Step 3

Check the Prometheus server.

- Open http://localhost:9090
- Access status -> Targets, both endpoints must be "UP"

## Step 4

Configure the Grafana.

- Open http://localhost:3000, user name and password are all `admin`
- Configure integration with Prometheus
    - Access configuration
    - Add data source
    - Select Prometheus
    - Use url "http://host.docker.internal:9090" and access with value "Server(default)"
- Configure dashboard
    - Access "home"
    - Import dashboard
    - Upload dashboard.json from /docker

# Monitoring with Splunk（Optional）

## Requirements

- Windows: install `Docker Desktop`
- Linux or MacOS: install `docker` and `docker-compose`

## Step 1

Use docker-compose to start Splunk servers.

- In the root folder

```
docker-compose -f docker-compose-splunk.yml up -d
```

## Step 2

Start `Resilience4jApplication`

## Step 3

Configure Splunk server

- Visit http://localhost:8000/
- Input username `admin` and password `splunkpwd1`
- Settings -> Data inputs -> splunk_hec_token -> Move `spring_metrics` to `Selected indexes` and save
- Input below contents in `Result` to check result

```
| mcatalog values(metric_name) where index=spring_metrics
```

```
| mstats max(_value) as memoryUsed where index=spring_metrics metric_name="jvm_memory_used.value" span=30s | eval memoryUsed=memoryUsed/(1024*1024)
| join type=left _time [
  | mstats max(_value) as memoryCommitted where index=spring_metrics metric_name="jvm_memory_committed.value" span=30s | eval memoryCommitted=memoryCommitted/(1024*1024)
] | timechart max(memoryUsed) as memoryUsed, max(memoryCommitted) as memoryCommitted span=30s
```

## TODO
- Add Resilience4j metrics dashboard
