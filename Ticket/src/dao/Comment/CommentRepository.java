package dao.Comment;

import domain.Comment;
import java.util.List;

public interface CommentRepository {
    List<Comment> getAllComments();
    List<Comment> getCommentsByTicketId(int ticketId);
    void createComment(Comment comment);
}
