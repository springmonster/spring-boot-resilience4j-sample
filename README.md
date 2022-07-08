# spring-cloud-resilience4j-complex-demo

# Getting Started

Start server
Application is running on http://localhost:8082

# Monitoring with Prometheus and Grafana（Optional）

## Requirements

- Windows: install `Docker Desktop`
- Linux or MacOS: install `docker` and `docker-compose`
 
## Step 1

Use docker-compose to start Grafana and Prometheus servers.

- In the root folder

```
docker-compose -f docker-compose-desktop.yml up -d
```

## Step 2

Check the Prometheus server.

- Open http://localhost:9090
- Access status -> Targets, both endpoints must be "UP"

## Step 3
Configure the Grafana.

- Open http://localhost:3000
- Configure integration with Prometheus
  - Access configuration
  - Add data source
  - Select Prometheus
  - Use url "http://host.docker.internal:9090" and access with value "Server(default)"
- Configure dashboard
  - Access "home"
  - Import dashboard
  - Upload dashboard.json from /docker
