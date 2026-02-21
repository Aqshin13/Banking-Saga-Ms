# 🏦 Banking Saga Microservices

Bu layihə Saga Pattern (Orchestration based) istifadə edən mikroservis arxitekturasıdır. Layihədə Customer Service, Purchase Service, Top-Up Service, Orchestrator Service, Apache Kafka və PostgreSQL istifadə olunur.

## 🚀 Projecti İşə Salmaq
Repository-ni clone edin:  
git clone https://github.com/Aqshin13/Banking-Saga-Ms

Docker qovluğuna keçin:  
```cd Banking-Saga-Ms/docker```

Docker compose ilə sistemi işə salın:  
```docker compose up -d```


## 🌐 Swagger UI Endpointlər
💸 Transfer Money (TopUp Service)  
http://localhost:8082/swagger-ui/index.html

👤 Customer Service  
http://localhost:8080/swagger-ui/index.html  
Buradan customer yaratmaq və balance yoxlamaq mümkündür.

🛒 Purchase & Refund Service  
http://localhost:8083/swagger-ui/index.html  
Buradan purchase və refund əməliyyatları edilə bilər.



