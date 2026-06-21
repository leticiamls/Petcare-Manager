INSERT INTO tb_sintoma (nome) VALUES
                                  ('Febre'),
                                  ('Apatia'),
                                  ('Coceira'),
                                  ('Vômito'),
                                  ('Diarreia'),
                                  ('Perda de apetite'),
                                  ('Tosse')
    ON CONFLICT DO NOTHING;

INSERT INTO tb_medicamento (nome, dose) VALUES
                                            ('Amoxicilina', '500mg'),
                                            ('Prednisolona', '20mg'),
                                            ('Metronidazol', '250mg'),
                                            ('Dipirona', '500mg'),
                                            ('Omeprazol', '10mg')
    ON CONFLICT DO NOTHING;