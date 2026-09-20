# Spring Boot Microservices + Kubernetes + CI/CD

Two microservices:
- user-service: REST API on port 8081
- order-service: REST API on port 8082; calls user-service

Stack: Java 21, Spring Boot 3.5.x, Maven, JUnit 5, Mockito, Docker, Kubernetes, GitHub Actions, optional Argo CD.

## Local test
cd user-service && mvn clean test
cd ../order-service && mvn clean test

Test reports: target/surefire-reports/

## Run locally
Terminal 1:
cd user-service && mvn spring-boot:run

Terminal 2:
cd order-service && mvn spring-boot:run

curl http://localhost:8081/api/users
curl http://localhost:8082/api/orders

Create user:
curl -X POST http://localhost:8081/api/users -H "Content-Type: application/json" -d '{"name":"Ashok","email":"ashok@example.com"}'

Create order:
curl -X POST http://localhost:8082/api/orders -H "Content-Type: application/json" -d '{"userId":1,"product":"Laptop","quantity":1}'

## Docker
mvn -f user-service/pom.xml clean package
mvn -f order-service/pom.xml clean package
docker build -t user-service:1.0.0 ./user-service
docker build -t order-service:1.0.0 ./order-service

For Minikube:
eval $(minikube docker-env)
then build the images again.

For Kind:
kind load docker-image user-service:1.0.0
kind load docker-image order-service:1.0.0

## Kubernetes
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/user-service.yaml
kubectl apply -f k8s/order-service.yaml
kubectl get pods -n microservices-demo
kubectl get svc -n microservices-demo

Access:
kubectl port-forward -n microservices-demo svc/user-service 8081:8081
kubectl port-forward -n microservices-demo svc/order-service 8082:8082

Inside Kubernetes, order-service calls http://user-service:8081 using Kubernetes Service DNS.

## CI
.github/workflows/ci.yml:
1. checkout
2. Java 21
3. Maven tests
4. package JARs
5. Docker build
6. Trivy HIGH/CRITICAL image scan

For a real registry deployment, add registry authentication and push only after security gates pass.

## GitOps
argocd/application.yaml demonstrates Argo CD watching the k8s directory. Replace YOUR-ORG/YOUR-REPO with the Git repository.
