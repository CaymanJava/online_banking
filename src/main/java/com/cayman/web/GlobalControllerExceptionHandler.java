package com.cayman.web;

import com.cayman.util.exceptions.*;
import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalControllerExceptionHandler {
    protected final static Logger LOG = Logger.getLogger(GlobalControllerExceptionHandler.class);
    private final static String ACCOUNT_IS_BLOCKED = "account.blocked";
    private final static String INCORRECT_AMOUNT = "incorrect.amount";
    private final static String ACCOUNT_IS_NOT_EXIST = "account.not.exist";
    private final static String NOT_ENOUGH_MONEY = "not.enough.money";
    private final static String CANNOT_DELETE = "cannot.delete";
    private final static String ALREADY_EXIST = "already.exist";
    private final static String MESSAGE_ERROR = "message.error";
    private final static String EDIT_SUPER_ADMIN = "edit.super.admin";

    @ExceptionHandler(Exception.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        LOG.warn("Exception: " + req.getRequestURL());
        ModelAndView mav = new ModelAndView("errors/exception");

        mav.addObject("message", e.getMessage());

        if (e instanceof NotAvailableAccountException) {
            mav.addObject("message", ACCOUNT_IS_BLOCKED);
        }

        if (e instanceof IncorrectAmountException || e instanceof NumberFormatException) {
            mav.addObject("message", INCORRECT_AMOUNT);
        }

        if (e instanceof NotFoundEntityException) {
            mav.addObject("message", ACCOUNT_IS_NOT_EXIST);
        }

        if (e instanceof NotEnoughMoneyInTheAccountException) {
            mav.addObject("message", NOT_ENOUGH_MONEY);
        }

        if (e instanceof ConstraintViolationException || e instanceof DataIntegrityViolationException) {
            mav.addObject("message", ALREADY_EXIST);
        }

        if (e instanceof CannotDeleteEntityException) {
            mav.addObject("message", CANNOT_DELETE);
        }

        if (e instanceof CannotSendMessageException) {
            mav.addObject("message", MESSAGE_ERROR);
        }

        if (e instanceof DoNotTouchSuperAdminException) {
            mav.addObject("message", EDIT_SUPER_ADMIN);
        }

        return mav;
    }
}
