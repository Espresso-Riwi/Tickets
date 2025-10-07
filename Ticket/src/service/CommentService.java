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

    public boolean updateComment(int commentId, String newContent) {
        try {
            Comment existingComment = commentRepository.getCommentById(commentId);
            if (existingComment == null) {
                System.out.println("Error: Comment does not exist.");
                return false;
            }

            if (newContent == null || newContent.trim().isEmpty()) {
                System.out.println("Error: Comment content cannot be empty.");
                return false;
            }

            existingComment.setContent(newContent.trim());
            commentRepository.updateComment(existingComment);
            System.out.println("Comment updated successfully.");
            return true;

        } catch (Exception e) {
            System.out.println("Error updating comment: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteComment(int commentId) {
        try {
            Comment comment = commentRepository.getCommentById(commentId);
            if (comment == null) {
                System.out.println("Error: Comment does not exist.");
                return false;
            }

            commentRepository.deleteComment(commentId);
            System.out.println("Comment deleted successfully.");
            return true;

        } catch (Exception e) {
            System.out.println("Error deleting comment: " + e.getMessage());
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

    public void deleteCommentsByTicketId(int ticketId) {
        commentRepository.deleteCommentsByTicketId(ticketId);
    }
}
