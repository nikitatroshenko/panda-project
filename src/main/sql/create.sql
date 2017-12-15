CREATE TABLE "PP_USERS" (
  "user_id" NUMBER,
  "username" VARCHAR UNIQUE,
  "role_id" NUMBER,
  "project_id" NUMBER NULL,
  PRIMARY KEY ("user_id")
);

CREATE INDEX "USER_NAME_IDX" ON  "PP_USERS" ("username");

CREATE TABLE "PP_ROLES" (
  "role_id" NUMBER,
  "name" VARCHAR,
  PRIMARY KEY ("role_id")
);

CREATE TABLE "PP_PROJECTS" (
  "project_id" NUMBER,
  "manager" NUMBER,
  "project_key" VARCHAR UNIQUE,
  "description" VARCHAR NULL,
  PRIMARY KEY ("project_id")
);

CREATE TABLE "PP_TICKETS" (
  "ticket_key" VARCHAR UNIQUE,
  "project_id" NUMBER,
  "assignee" NUMBER,
  "name" VARCHAR,
  "ticket_status_id" NUMBER,
  "due_date" DATE NULL,
  "ticket_type_id" NUMBER,
  "description" VARCHAR NULL
);

CREATE INDEX "TICKET_KEY_IDX" ON  "PP_TICKETS" ("ticket_key");

CREATE INDEX "TICKET_PROJECT_IDX" ON  "PP_TICKETS" ("project_id");

CREATE INDEX "TICKET_USER_IDX" ON  "PP_TICKETS" ("assignee");

CREATE INDEX "TICKET_STATUS_IDX" ON  "PP_TICKETS" ("ticket_status_id");

CREATE INDEX "TICKET_DUE_IDX" ON  "PP_TICKETS" ("due_date");

CREATE INDEX "TICKET_TYPE_IDX" ON  "PP_TICKETS" ("ticket_type_id");

CREATE TABLE "PP_TICKET_STATUSES" (
  "ticket_status_id" NUMBER,
  "name" VARCHAR,
  PRIMARY KEY ("ticket_status_id")
);

CREATE TABLE "PP_TICKET_TYPES" (
  "ticket_type_id" NUMBER,
  "name" VARCHAR,
  PRIMARY KEY ("ticket_type_id")
);
