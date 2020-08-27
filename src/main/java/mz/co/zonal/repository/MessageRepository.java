package mz.co.zonal.repository;

import mz.co.zonal.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    int countByProductIdAndSenderIdAndReceiverId(
            Long productId, Long senderId, Long receiverId
    );

    int countByProductIdAndReceiverIdAndSenderId(
            Long productId, Long senderId, Long receiverId
    );

}
