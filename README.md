# Ticketing System in JAVA README for devs

# Data Base

## Entities

- **User**: Represents a user in the system.
  - Attributes: `user_id`, `name`, `dni`, `email`, `role`
  - Roles: `reporter`, `assignee`
  - Relationships: Can report multiple `Issues`, can be assigned to multiple `Issues`.
  - methods: I don't know
  - constraints: `dni` must be unique.
  - validations: `name` must not be empty, `role` must be either `reporter` or `assignee`.
  - indexes: `user_id`
  - primary key: `user_id`
-------
- **Ticket**: Represent a ticket in the system.
- Attributes: `ticket_id`, `title`, `description`, `status`, `priority`, `id_reporter`, `id_assignee`, `id_category`
  - Statuses: `open`, `in_progress`, `closed`
  - Priorities: `low`, `medium`, `high`
  - Relationships: Reported by one `User` (reporter), assigned to one `User` (assignee), can have multiple `Comments`.
  - methods: I don't know
  - constraints: `title` must not be empty, `status` must be one of the defined statuses, `priority` must be one of the defined priorities.
  - validations: `reporter_id` and `assignee_id` must reference valid `User` ids.
  - indexes: `ticket_id`
  - primary key: `ticket_id`
  - foreign keys: `reporter_id` references `User(user_id)`, `assignee_id` references `User(user_id)`
-------
- **Comment**: Represents a comment on a ticket.
  - Attributes: `comment_id`, `ticket_id`, `user_id`, `content`
  - Relationships: Belongs to one `Ticket`, made by one `User`.
  - methods: I don't know
  - constraints: `content` must not be empty.
  - validations: `ticket_id` must reference a valid `Ticket` id, `user_id` must reference a valid `User` id.
  - indexes: `comment_id`
  - primary key: `comment_id`
  - foreign keys: `ticket_id` references `Ticket(ticket_id)`, `user_id` references `User(user_id)`
-------
**Category**: Represents a category for tickets.
  - Attributes: `category_id`, `name`, `description`
  - Relationships: Can be associated with multiple `Tickets`.
  - methods: I don't know
  - constraints: `name` must be unique and not empty.
  - validations: `description` can be optional.
  - indexes: `category_id`
  - primary key: `category_id`

```sql
CREATE TABLE user(
    user_id INT PRIMARY KEY auto_increment,
    name VARCHAR(255),
    dni VARCHAR(60) unique not null,
    email VARCHAR(255) unique not null,
    rol ENUM('reporter', 'assignee') NOT NULL
);

CREATE TABLE category(
    category_id INT PRIMARY KEY auto_increment,
    name VARCHAR(50) unique not null,
    description TEXT
);

CREATE TABLE ticket(
    ticket_id INT PRIMARY KEY auto_increment,
    title VARCHAR(60),
    description TEXT,
    status ENUM('open', 'in_progress', 'closed') NOT NULL,
    priority ENUM('low', 'medium', 'high'),
    reporter_id INT,
    assignee_id INT,
    category_id INT,
    FOREIGN KEY (reporter_id) REFERENCES user(user_id) ON DELETE SET NULL,
    FOREIGN KEY (assignee_id) REFERENCES user(user_id) ON DELETE SET NULL,
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE SET NULL
);

CREATE TABLE comment(
    comment_id INT PRIMARY KEY auto_increment,
    ticket_id INT,
    user_id INT,
    content TEXT not null,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE SET NULL,
    FOREIGN KEY (ticket_id) REFERENCES ticket(ticket_id) ON DELETE SET NULL
);
```

## Relationships
- A `User` can report multiple `Tickets` (one-to-many relationship).
- A `User` can be assigned to multiple `Tickets` (one-to-many relationship).
- A `Ticket` can have multiple `Comments` (one-to-many relationship).
  - Each `Comment` is made by one `User` and belongs to one `Ticket` (many-to-one relationship).
  - Each `Ticket` is reported by one `User` (reporter) and assigned to one `User` (assignee) (many-to-one relationship).
  - Each `User` can report multiple `Tickets` and be assigned to multiple `Tickets` (one-to-many relationship).
  - Each `User` can make multiple `Comments` (one-to-many relationship).

# MVC Architecture with Java using dao pattern
## Model
- **User**: Represents a user in the system.
  - Attributes: `userId`, `name`, `dni`, `role`
  - Methods: I don't know
  - Validations: `name` must not be empty, `role` must be either `reporter` or `assignee`.
-------
- **Ticket**: Represents a ticket in the system.
  - Attributes: `ticketId`, `title`, `description`, `status`, `priority`, `reporterId`, `assigneeId`, `category`
  - Methods: I don't know
  - Validations: `title` must not be empty, `status` must be one of the defined statuses, `priority` must be one of the defined priorities.
-------
- **Comment**: Represents a comment on a ticket.
  - Attributes: `commentId`, `ticketId`, `userId`, `content`, `dateCreated`, `timeCreated`
  - Validations: `content` must not be empty
-------
## View
- **UserView**: Handles the presentation of user-related information.
  - Methods: `displayUserDetails()`, `displayUserTickets()`, `displayAllUsers()`, `displayUsersByRole()`, `displayUserComments()`
-------
- **TicketView**: Handles the presentation of ticket-related information.
  - Methods: `displayTicketDetails()`, `displayTicketComments()`, `displayAllTickets()`, `displayTicketsByAssignee()`
-------
- **CommentView**: Handles the presentation of comment-related information.
  - Methods: `displayCommentDetails()`, `displayCommentsByTicket()`
  - -------
## Service
- **UserService**: Contains business logic related to users.
  - Methods: `createUser()`, `getUserById()`, `updateUser()`, `deleteUser()`, `topReportCategories()`, `getUsersByRole()`
-------
- **TicketService**: Contains business logic related to tickets.
  - Methods: `createTicket()`, `getTicketById()`, `updateTicket()`, `deleteTicket()`, `assignTicket()`, `changeTicketStatus()`, `AssigneeReports()`
-------
- **CommentService**: Contains business logic related to comments.
  - Methods: `addComment()`, `getCommentsByTicketId()`, `deleteComment()`, `updateComment()`, `getCommentsByUserId()`
  - -------
## Controller
- **UserController**: Manages user-related operations.
  - Methods: `createUser()`, `getUserById()`, `updateUser()`, `deleteUser()`, `getUsersByRole()`, `topReportCategories()`
-------
- **TicketController**: Manages ticket-related operations.
  - Methods: `createTicket()`, `getTicketById()`, `updateTicket()`, `deleteTicket()`, `assignTicket()`, `changeTicketStatus()`
-------
- **CommentController**: Manages comment-related operations.
  - Methods: `addComment()`, `getCommentsByTicketId()`, `deleteComment()`, `updateComment()`, `getCommentsByUserId()`
  - -------
## DAO (Data Access Object)
- **UserDAO**: Handles database operations for `User` entity.
  - Methods: `createUser()`, `getUserById()`, `updateUser()`, `deleteUser()`, `getUsersByRole()`, `topReportCategories()`
-------
- **TicketDAO**: Handles database operations for `Ticket` entity.
  - Methods: `createTicket()`, `getTicketById()`, `updateTicket()`, `deleteTicket()`, `assignTicket()`, `changeTicketStatus()`
-------
- **CommentDAO**: Handles database operations for `Comment` entity.
  - Methods: `addComment()`, `getCommentsByTicketId()`, `deleteComment()`, `updateComment()`, `getCommentsByUserId()`
  - -------

# Another queries
## SQL Queries
- Top report categories by number of tickets from highest to lowest.
```sql
SELECT category, COUNT(*) AS ticket_count
FROM Ticket
GROUP BY category
ORDER BY ticket_count DESC
LIMIT 3;
```
- Assignee reports with all ticket attributes
```sql
SELECT u.name AS assignee_name, t.*
FROM User u
JOIN Ticket t ON u.user_id = t.assignee_id
WHERE u.role = 'assignee';
```