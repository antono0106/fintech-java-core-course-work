package com.moroz.service.statusprocessors;

import com.moroz.logging.CustomLogger;
import com.moroz.persistence.entites.PaymentEntity;
import com.moroz.persistence.entites.TicketEntity;
import com.moroz.persistence.enums.PaymentStatus;
import com.moroz.persistence.enums.TicketStatus;
import com.moroz.service.PaymentService;
import com.moroz.service.TicketService;

import java.util.List;
import java.util.TimerTask;

/**
 * @author : anton
 * @since : 21.02.2022, пн
 **/
public class TicketStatusTask extends TimerTask {
    static final CustomLogger logger = new CustomLogger(TicketStatusTask.class);

    private final TicketService ticketService = new TicketService();
    private final PaymentService paymentService = new PaymentService();

    @Override
    public void run() {
        logger.info("Processing tickets...");
        List<TicketEntity> entityList = ticketService.findAll();

        for (TicketEntity entity: entityList) {
            if(entity.getStatus().equals(TicketStatus.PROCESSING)) {
                if (entity.getPaymentEntity() == null || paymentService.findById(entity.getPaymentEntity().getId()) == null) {
                    continue;
                }

                PaymentEntity paymentEntity = paymentService.findById(entity.getPaymentEntity().getId());

                if(paymentEntity.getStatus().equals(PaymentStatus.DONE)) {
                    ticketService.updateStatus(entity, TicketStatus.DONE);
                } else if (paymentEntity.getStatus().equals(PaymentStatus.FAILED)) {
                    ticketService.updateStatus(entity, TicketStatus.FAILED);
                }
            }
        }
    }
}
