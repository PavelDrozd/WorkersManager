FROM mysql:8.0.32
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=workersmanager
WORKDIR opt/app

COPY data.sql docker/mysql/
COPY schema.sql docker/mysql/

