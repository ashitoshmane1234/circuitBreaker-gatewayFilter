<h1>Secure Gate</h1>

<h2>Project Description</h2>
<p>Secure Gate is a custom API Gateway solution designed to enhance the security, performance, and monitoring of your microservices architecture. It provides advanced features such as routing, centralized authentication, rate limiting, and detailed monitoring and analytics. Secure Gate leverages powerful technologies like Spring Reactive Cloud Gateway, Prometheus, Grafana, Redis, and Loki to offer a comprehensive and customizable solution for modern API management.</p>

<h2>Technologies Used</h2>
<ul>
    <li><strong>Spring Reactive Cloud Gateway</strong> - For handling routing and gateway functionalities.</li>
    <li><strong>Prometheus</strong> - For collecting and storing metrics.</li>
    <li><strong>Grafana</strong> - For visualizing metrics and creating monitoring dashboards.</li>
    <li><strong>Redis</strong> - For caching and rate limiting.</li>
</ul>

<h2>Installation Instructions</h2>

<h3>1. Clone the Project</h3>
<p>Clone the repository to your local machine:</p>
<pre><code>git clone git@bitbucket.org:Mindbowser/secure-gate.git</code><br><code>cd secure-gate</code></pre>

<h3>2. Install Dependencies with Docker</h3>
<p>To get started quickly, you can use Docker to run Grafana, Prometheus, and Redis. Here are the steps:</p>

<h4>Install Redis:</h4>
<pre><code>docker run --name redis -d -p 6379:6379 redis</code></pre>

<h4>Install Prometheus:</h4>
<p>Create a file named <code>prometheus.yml</code> with the following content:</p>
<pre><code>global:
  scrape_interval: 15s
scrape_configs:
  - job_name: 'prometheus'
    static_configs:
      - targets: ['localhost:9090']
  - job_name: 'secure-gate'
    static_configs:
      - targets: ['localhost:8080']</code></pre>

<p>Then run Prometheus using Docker:</p>
<pre><code>docker run --name prometheus -d -p 9090:9090 \
-v $(pwd)/prometheus.yml:/etc/prometheus/prometheus.yml \
prom/prometheus</code></pre>

<h4>Install Grafana:</h4>
<pre><code>docker run --name=grafana -d -p 3000:3000 grafana/grafana</code></pre>

<h3>3. Set Up Configuration</h3>
<p>Secure Gate uses an <code>application.yaml</code> file for configuring various features such as routing, authentication, and monitoring. Update the <code>application.yaml</code> file located in the <code>src/main/resources</code> directory with your environment-specific settings.</p>

<h4>Sample <code>application.yaml</code> Configuration:</h4>
<pre><code>server:
  port: 8080

spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      routes:
        - id: patient-service
          uri: http://localhost:8001
          predicates:
            - Path=/m1/**
        - id: provider-service
          uri: http://localhost:8002
          predicates:
            - Path=/m2/**
      globalcors:
        cors-configurations:
          '[/**]':
            allowed-origins:
              - "*"
            allowed-methods:
              - GET
              - POST
              - PATCH
              - PUT
              - DELETE
              - OPTIONS
            allowed-headers:
              - Authorization
              - Requestor-Type
              - Origin
              - Content-Type
              - Version
            exposed-headers:
              - Authorization
              - Requestor-Type
              - Origin
              - Content-Type
              - Version
            max-age: 3600
      default-filters:
        - name: RequestRateLimiter
          args:
            redis-rate-limiter:
              replenishRate: 1
              burstCapacity: 10
              requestedTokens: 1
            key-resolver: "#{@userKeyResolver}"
      metrics:
        enabled: true
        tags:
          path:
            enabled: true
management:
  endpoints:
    web:
      exposure:
        include:
        - "*"
  endpoint:
    metrics:
      enabled: true
jwt:
  config:
   enabled: true
   jwk-set:
      url: "https://your-auth-domain/.well-known/jwks.json"
      key-id: "your-key-id"</code></pre>

<h3>Understanding the <code>application.yaml</code> Configuration</h3>
<p>The <code>application.yaml</code> file is where you define how Secure Gate will behave. Here's a breakdown of key sections:</p>
<ul>
    <li><strong>server.port:</strong> Specifies the port on which the gateway will run.</li>
    <li><strong>spring.cloud.gateway.routes:</strong> Defines the routes for your microservices. Each route has an <code>id</code>, a <code>uri</code> where the service is located, and a set of <code>predicates</code> that determine which requests should be routed to this service.</li>
    <li><strong>globalcors:</strong> Configures CORS (Cross-Origin Resource Sharing) settings, allowing you to control which domains can interact with your API.</li>
    <li><strong>default-filters:</strong> Applies filters globally to all routes, such as rate limiting. The Redis-based rate limiter is configured here.</li>
    <li><strong>metrics:</strong> Enables metrics collection, which can be scraped by Prometheus for monitoring and analysis.</li>
    <li><strong>jwt.config:</strong> Configures JWT-based authentication for securing your APIs.</li>
</ul>

<h3>4. Build and Run the Project</h3>
<p>Build the project using Maven and run the application:</p>
<pre><code>./mvnw clean install</code><br>
<code>./mvnw spring-boot:run</code></pre>

<h3>5. Accessing the Application</h3>
<p>Once the application is running, you can access the Secure Gate API Gateway at <code>http://localhost:8080</code>.</p>

<h2>Usage</h2>
<p>Secure Gate enhances the security and reliability of your system by offering features like advanced routing, centralized authentication, rate limiting, and real-time monitoring. The Grafana dashboard provides insights into API usage, system performance, and potential security threats.</p>

<h3>Key Features:</h3>
<ul>
    <li><strong>Routing:</strong> Define routes for different services using the <code>application.yaml</code> file.</li>
    <li><strong>Centralized Authentication:</strong> Secure your APIs with JWT-based authentication.</li>
    <li><strong>Rate Limiting:</strong> Protect your services from abuse by limiting the number of requests.</li>
    <li><strong>Monitoring and Analytics:</strong> Track metrics and visualize them using Prometheus and Grafana.</li>
</ul>

<h2>Monitoring and Analytics Setup</h2>
<p>To set up monitoring and analytics:</p>
<ol>
    <li><strong>Prometheus:</strong> Configure Prometheus to scrape metrics from Secure Gate.</li>
    <li><strong>Grafana:</strong> Import dashboards in Grafana to visualize metrics collected by Prometheus.</li>
</ol>

<h2>Contributors</h2>
<ul>
    <li><strong>Rohit Kavthekar</strong></li>
    <li><strong>Amaresh Joshi</strong></li>
    <li><strong>Rohan Shrivastava</strong></li>
    <li><strong>Abhi Shrivastave</strong></li>
</ul>

<h2>License</h2>
<p>This project is licensed under the MIT License - see the <a href="LICENSE">LICENSE</a> file for details.</p>