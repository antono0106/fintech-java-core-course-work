package com.moroz.service.statusprocessors;

import com.moroz.logging.CustomLogger;
import com.moroz.persistence.entites.PaymentEntity;
import com.moroz.persistence.enums.PaymentStatus;
import com.moroz.service.PaymentService;

import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * @author : anton
 * @since : 19.02.2022, сб
 **/
public class PaymentStatusTask extends TimerTask {
    static final CustomLogger logger = new CustomLogger(PaymentStatusTask.class);

    private final PaymentService paymentService = new PaymentService();

    private final List<PaymentStatus> statuses = List.of(PaymentStatus.NEW, PaymentStatus.DONE, PaymentStatus.FAILED);

    @Override
    public void run() {
        logger.info("Processing payment statuses");

        List<PaymentEntity> entities = paymentService.findAll();

        int counterForFinalStatuses = 0;
        for (PaymentEntity paymentEntity: entities) {
            if (paymentEntity.getStatus().equals(PaymentStatus.NEW)) {
                int random = (Math.random() <= 0.5) ? 1 : 2;
                paymentService.updateStatus(paymentEntity, statuses.get(random));
            }
        }

    }
}
