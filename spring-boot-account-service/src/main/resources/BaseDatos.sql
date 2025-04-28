DROP TABLE IF EXISTS cuenta CASCADE;
DROP TABLE IF EXISTS movimiento CASCADE;
-- Crear la tabla cuenta
	CREATE TABLE IF NOT EXISTS cuenta (
	    id BIGSERIAL PRIMARY KEY,
	    numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
	    tipo_cuenta VARCHAR(50),
	    saldo_inicial DECIMAL(10, 2) DEFAULT 0.00,
	    estado BOOLEAN DEFAULT TRUE,
	    cliente_id BIGINT NOT NULL,
	    FOREIGN KEY (cliente_id) REFERENCES cliente(clienteId) ON DELETE CASCADE
	);
	
-- Crear la tabla movimiento
	CREATE TABLE IF NOT EXISTS movimiento (
	    id BIGSERIAL PRIMARY KEY,
	    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	    tipo_movimiento VARCHAR(50),
	    valor DECIMAL(10, 2) NOT NULL,
	    saldo DECIMAL(10, 2) NOT NULL,
	    cuenta_id BIGINT NOT NULL,
	    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id) ON DELETE CASCADE
	);