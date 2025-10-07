package service;

import dao.Comment.CommentRepository;
import dao.User.UserRepository;
import dao.Ticket.TicketRepository;
import domain.Comment;
import domain.User;
import domain.Ticket;
import java.util.List;

public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private TicketRepository ticketRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, TicketRepository ticketRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.getAllComments();
    }

    public List<Comment> getCommentsByTicketId(int ticketId) {
        return commentRepository.getCommentsByTicketId(ticketId);
    }

    public Comment getCommentById(int commentId) {
        return commentRepository.getCommentById(commentId);
    }

    public boolean createComment(int ticketId, String userDni, String content) {
        try {
            Ticket ticket = ticketRepository.getTicketById(ticketId);
            if (ticket == null) {
                System.out.println("Error: Ticket does not exist.");
                return false;
            }

            User user = userRepository.getUserByDni(userDni);
            if (user == null) {
                System.out.println("Error: User does not exist.");
                return false;
            }

            if (content == null || content.trim().isEmpty()) {
                System.out.println("Error: Comment content cannot be empty.");
                return false;
            }

            Comment comment = new Comment(ticketId, user.getUser_id(), content.trim());
            commentRepository.createComment(comment);
            System.out.println("Comment created successfully.");
            return true;

        } catch (Exception e) {
            System.out.println("Error creating comment: " + e.getMessage());
            return false;
        }
    }


    public boolean canUserModifyComment(int commentId, String userDni) {
        try {
            Comment comment = commentRepository.getCommentById(commentId);
            User user = userRepository.getUserByDni(userDni);

            if (comment == null || user == null) {
                return false;
            }

            return comment.getUserId() == user.getUser_id();
        } catch (Exception e) {
            return false;
        }
    }
}
