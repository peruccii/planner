package com.perucci.planner.repositories;
import java.util.List;


public interface IEmailServiceRepository {
    void sendEmail(List<String> to, String subject, String body);
}
