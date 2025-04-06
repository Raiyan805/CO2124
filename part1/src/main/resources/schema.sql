-- Create the 'Doctor' table
CREATE TABLE IF NOT EXISTS doctor (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
    specialisation VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15)
    );

-- Create the 'Patient' table
CREATE TABLE IF NOT EXISTS patient (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    address TEXT
    );

-- Create the 'Appointments' table
CREATE TABLE IF NOT EXISTS appointments (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            appointment_date TIMESTAMP NOT NULL,
                                            status VARCHAR(255) CHECK (status IN ('SCHEDULED', 'COMPLETED', 'CANCELLED')),
    notes TEXT,
    patient_id BIGINT,
    doctor_id BIGINT,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE
    );

-- Create the 'Record' table
CREATE TABLE IF NOT EXISTS record (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      record_date TIMESTAMP NOT NULL,
                                      diagnosis TEXT,
                                      treatment TEXT,
                                      notes TEXT,
                                      appointment_id BIGINT,
                                      FOREIGN KEY (appointment_id) REFERENCES appointments(id) ON DELETE CASCADE
    );
