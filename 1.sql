CREATE TABLE `bus_number` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `number` INTEGER
);

CREATE TABLE `bus_station` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `name` TEXT
);

CREATE TABLE `schedule` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `time` TIME,
  `bus_station` INTEGER,
  `bus_number` INTEGER,
  `direction` INTEGER
);

ALTER TABLE `schedule` ADD CONSTRAINT `schedule_fk1` FOREIGN KEY (`bus_station`) REFERENCES bus_station(`id`);
ALTER TABLE `schedule` ADD CONSTRAINT `schedule_fk2` FOREIGN KEY (`bus_number`) REFERENCES bus_number(`id`);