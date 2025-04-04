-- Populate doctor table
INSERT INTO doctor (email, name, phone_number, specialisation) VALUES
                                                                   ('smith.john@hospital.com', 'Dr. John Smith', '555-123-4567', 'Cardiology'),
                                                                   ('chen.mei@hospital.com', 'Dr. Mei Chen', '555-234-5678', 'Neurology'),
                                                                   ('patel.ravi@hospital.com', 'Dr. Ravi Patel', '555-345-6789', 'Pediatrics'),
                                                                   ('rodriguez.ana@hospital.com', 'Dr. Ana Rodriguez', '555-456-7890', 'Orthopedics'),
                                                                   ('kim.david@hospital.com', 'Dr. David Kim', '555-567-8901', 'Dermatology'),
                                                                   ('johnson.sarah@hospital.com', 'Dr. Sarah Johnson', '555-678-9012', 'Internal Medicine'),
                                                                   ('baker.james@hospital.com', 'Dr. James Baker', '555-789-0123', 'Ophthalmology'),
                                                                   ('nguyen.lisa@hospital.com', 'Dr. Lisa Nguyen', '555-890-1234', 'Psychiatry'),
                                                                   ('williams.michael@hospital.com', 'Dr. Michael Williams', '555-901-2345', 'Endocrinology'),
                                                                   ('garcia.elena@hospital.com', 'Dr. Elena Garcia', '555-012-3456', 'Gastroenterology');

-- Populate patient table
INSERT INTO patient (address, email, name, phone_number) VALUES
                                                             ('123 Main St, Anytown', 'twalker@email.com', 'Tom Walker', '555-111-2222'),
                                                             ('456 Oak Ave, Somecity', 'mjones@email.com', 'Mary Jones', '555-222-3333'),
                                                             ('789 Pine Rd, Otherville', 'dclark@email.com', 'David Clark', '555-333-4444'),
                                                             ('321 Elm Blvd, Downtown', 'swilson@email.com', 'Susan Wilson', '555-444-5555'),
                                                             ('654 Maple Dr, Uptown', 'rthomas@email.com', 'Robert Thomas', '555-555-6666'),
                                                             ('987 Cedar Ln, Westside', 'lblack@email.com', 'Linda Black', '555-666-7777'),
                                                             ('147 Birch Ct, Eastside', 'jwong@email.com', 'James Wong', '555-777-8888'),
                                                             ('258 Spruce Way, Northend', 'psmith@email.com', 'Patricia Smith', '555-888-9999'),
                                                             ('369 Fir Pl, Southside', 'klee@email.com', 'Kevin Lee', '555-999-0000'),
                                                             ('741 Redwood Ter, Suburb', 'mbrown@email.com', 'Michelle Brown', '555-000-1111'),
                                                             ('852 Aspen Cir, Outskirts', 'jadams@email.com', 'John Adams', '555-111-3333'),
                                                             ('963 Willow Ave, Lakeside', 'elopez@email.com', 'Emma Lopez', '555-222-4444'),
                                                             ('159 Poplar St, Hillside', 'cgonzalez@email.com', 'Carlos Gonzalez', '555-333-5555'),
                                                             ('357 Chestnut Rd, Riverside', 'hnguyen@email.com', 'Hannah Nguyen', '555-444-6666'),
                                                             ('951 Walnut Blvd, Mountainview', 'dkim@email.com', 'Daniel Kim', '555-555-7777');

-- Populate appointments table (with a mix of statuses)
INSERT INTO appointments (appointment_date, notes, status, doctor_id, patient_id) VALUES
                                                                                      ('2025-03-15 09:00:00', 'Follow-up on blood pressure', 'COMPLETED', 1, 3),
                                                                                      ('2025-03-18 10:30:00', 'Annual checkup', 'COMPLETED', 6, 5),
                                                                                      ('2025-03-25 13:45:00', 'Headache consultation', 'COMPLETED', 2, 1),
                                                                                      ('2025-03-28 11:15:00', 'Skin rash examination', 'COMPLETED', 5, 8),
                                                                                      ('2025-04-01 14:30:00', 'Vaccination appointment', 'COMPLETED', 3, 10),
                                                                                      ('2025-04-02 09:45:00', 'Eye examination', 'COMPLETED', 7, 2),
                                                                                      ('2025-04-03 16:00:00', 'Therapy session', 'COMPLETED', 8, 7),
                                                                                      ('2025-04-04 08:30:00', 'Morning consultation', 'SCHEDULED', 4, 12),
                                                                                      ('2025-04-04 15:30:00', 'Evening consultation', 'SCHEDULED', 10, 6),
                                                                                      ('2025-04-05 12:00:00', 'Lunch hour appointment', 'SCHEDULED', 9, 11),
                                                                                      ('2025-04-06 10:00:00', 'Weekend visit', 'SCHEDULED', 2, 9),
                                                                                      ('2025-04-07 11:30:00', 'Prescription renewal', 'SCHEDULED', 6, 14),
                                                                                      ('2025-04-08 13:00:00', 'Routine checkup', 'SCHEDULED', 5, 15),
                                                                                      ('2025-04-09 14:15:00', 'Follow-up examination', 'SCHEDULED', 1, 13),
                                                                                      ('2025-04-10 16:45:00', 'Late afternoon consultation', 'SCHEDULED', 3, 4),
                                                                                      ('2025-03-20 09:30:00', 'Patient cancelled due to emergency', 'CANCELLED', 4, 6),
                                                                                      ('2025-03-22 13:15:00', 'Doctor had to reschedule', 'CANCELLED', 10, 2),
                                                                                      ('2025-03-27 15:00:00', 'Patient no-show', 'CANCELLED', 9, 3),
                                                                                      ('2025-03-30 12:45:00', 'Rescheduled to next month', 'CANCELLED', 7, 1),
                                                                                      ('2025-04-02 10:15:00', 'Patient requested cancellation', 'CANCELLED', 8, 5);

-- Populate records for COMPLETED appointments
INSERT INTO record (diagnosis, notes, record_date, treatment, appointment_id) VALUES
                                                                                  ('Hypertension', 'Blood pressure readings: 140/90', '2025-03-15 09:30:00', 'Prescribed lisinopril 10mg daily', 1),
                                                                                  ('Healthy', 'All vitals normal, bloodwork within range', '2025-03-18 11:00:00', 'No treatment necessary, continue healthy lifestyle', 2),
                                                                                  ('Tension headache', 'Patient reports increased stress at work', '2025-03-25 14:15:00', 'Recommended OTC pain relievers and stress management techniques', 3),
                                                                                  ('Contact dermatitis', 'Likely caused by new laundry detergent', '2025-03-28 11:45:00', 'Prescribed hydrocortisone cream and advised to change detergent', 4),
                                                                                  ('Routine vaccination', 'Administered flu and Tdap vaccines', '2025-04-01 15:00:00', 'None, routine preventative care', 5),
                                                                                  ('Presbyopia', 'Age-related vision changes', '2025-04-02 10:15:00', 'Prescribed reading glasses +1.5', 6),
                                                                                  ('Generalized anxiety disorder', 'Patient responding well to cognitive behavioral therapy', '2025-04-03 16:30:00', 'Continue weekly therapy sessions, mindfulness exercises', 7);