package org.intermine.web.struts;

/*
 * Copyright (C) 2002-2018 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.intermine.web.logic.session.SessionMethods;

/**
 * An InterMine specific version of Action.
 *
 * @author Kim Rutherford
 */

public class InterMineAction extends Action
{
    /**
     * Add the given ActionMessage as a message for this Action
     * @param actionMessage the message to save
     * @param request the HTTP request we are processing
     */
    public void recordMessage(ActionMessage actionMessage, HttpServletRequest request) {
        ActionMessages actionMessages = getMessages(request);
        actionMessages.add(ActionMessages.GLOBAL_MESSAGE, actionMessage);
        saveMessages(request, actionMessages);
    }

    /**
     * Add the given ActionMessage as an error for this Action
     * @param actionMessage the message to save
     * @param request the HTTP request we are processing
     */
    public void recordError(ActionMessage actionMessage, HttpServletRequest request) {
        recordError(actionMessage, request, null);
    }

    /**
     * Add the given ActionMessage as an error for this Action
     * @param actionMessage the message to save
     * @param request the HTTP request we are processing
     * @param e the Exception that caused this error
     */
    public void recordError(ActionMessage actionMessage, HttpServletRequest request,
                            Exception e) {
        recordError(actionMessage, request, e, null);
    }

    /**
     * Add the given ActionMessage as an error for this Action and log the error
     * @param actionMessage the message to save
     * @param request the HTTP request we are processing
     * @param exception the Exception that caused this error
     * @param logger the Logger to write the error message to
     */
    public void recordError(ActionMessage actionMessage, HttpServletRequest request,
                            Exception exception, Logger logger) {
        ActionMessages actionMessages = getErrors(request);
        actionMessages.add(ActionMessages.GLOBAL_MESSAGE, actionMessage);
        saveErrors(request, actionMessages);

        if (exception != null && logger != null) {
            logger.error(exception);
        }
    }

    /**
     * Get the web proprties.
     * @param request current request (from which we fetch the servlet context).
     * @return Properties
     */
    public static Properties getWebProperties(HttpServletRequest request) {
        return SessionMethods.getWebProperties(request.getSession().getServletContext());
    }
}
