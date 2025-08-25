 Spring Boot + Prometheus + Grafana Monitoring Setup

This guide shows how to monitor a **Spring Boot application** with **Prometheus** and **Grafana** using Docker Compose.

---

## 1. Add Spring Boot Actuator + Micrometer

First, expose metrics from your Spring Boot app.

### 👉 In `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

👉 In application.properties:
```
management.endpoints.web.exposure.include=*
management.endpoint.prometheus.enabled=true
management.metrics.export.prometheus.enabled=true
```
Now, when you run your app, metrics will be exposed at:

```
👉 http://localhost:8080/actuator/prometheus
```
Now, when you run your app, metrics will be exposed at:

### 👉 http://localhost:8080/actuator/prometheus


## 2. Create prometheus.yml

Prometheus needs to scrape your Spring Boot metrics.

Create a file prometheus.yml in your project root:
```
global:
  scrape_interval: 5s

scrape_configs:
  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['host.docker.internal:8080']

```
⚠️ Note:

host.docker.internal is used so Prometheus inside Docker can reach your local Spring Boot app.

If you later run Spring Boot inside Docker too, replace this with the container name.


3. Create docker-compose.yml

Now spin up Prometheus + Grafana with Docker Compose:
```
version: '3.8'
services:
  prometheus:
    image: prom/prometheus
    container_name: prometheus
    ports:
      - "9090:9090"
    volumes:
      - ./prometheus.yml:/etc/prometheus/prometheus.yml

  grafana:
    image: grafana/grafana
    container_name: grafana
    ports:
      - "3000:3000"
    depends_on:
      - prometheus


```
## 4. Start Containers

Run:

docker-compose up -d


Access the services:

### Prometheus → http://localhost:9090

### Grafana → http://localhost:3000

(Default login: admin/admin)

## 5. Configure Grafana

Log in to Grafana.

Go to Connections → Data Sources → Add new data source.

Choose Prometheus.

URL: http://prometheus:9090

Save & Test.

Import dashboards:

Go to Dashboards → Import.

Use Spring Boot Micrometer dashboard (ID: 4701).

✅ Result

You’ll now see Spring Boot metrics (JVM, HTTP requests, DB connections, etc.) in Grafana.
