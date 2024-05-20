#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE TABLE clients (
            client_id VARCHAR(50) PRIMARY KEY,
            name VARCHAR(50),
            email VARCHAR(100),
            cnpj VARCHAR(18),
            phone VARCHAR(18)
        );
EOSQL