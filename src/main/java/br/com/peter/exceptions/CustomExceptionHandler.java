package br.com.peter.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
 
//Initially we must implement the CustomExceptionHandler class that extends the class ExceptionHandlerWrapper
public class CustomExceptionHandler extends ExceptionHandlerWrapper {
 
    private ExceptionHandler wrapped;
 
    //Gets an instance of FacesContext
    final FacesContext facesContext = FacesContext.getCurrentInstance();
 
    //Gets a map of FacesContext
    final Map requestMap = facesContext.getExternalContext().getRequestMap();
 
    //Gets the current state of navigation between the JSF pages
    final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
 
    //Declares the constructor that takes an exception type Exception Handler as a parameter
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }
 
    //Overwrite the ExceptionHandler method that returns the "stack" of exceptions
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
 
    //Overriding the handle which is responsible for handling exceptions JSF
    @Override
    public void handle() throws FacesException {
 
        final Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();
        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
 
            // Retrieve the exception of context
            Throwable exception = context.getException();
 
            // Here we try to handle the exception
            try {
 
                // You could for example instantiate the StringWriter and PrintWriter classes
                StringWriter stringWriter = new StringWriter();
           		PrintWriter printWriter = new PrintWriter(stringWriter);
       			exception.printStackTrace(printWriter);
                // Finally you can convert the stack of exceptions on a String
                String message = stringWriter.toString();

                // Print the stack trace in the log
                exception.printStackTrace();
 
                // Place an exception message in the map request
                requestMap.put("exceptionMessage", exception.getMessage());
 
                // Warns the user of error
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_ERROR, "The system has recovered from an unexpected error.", ""));
 
                // Reassures the user for it to continue using the system
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_INFO, "You can continue using the system normally!", ""));
 
                // Arrow navigation to a default page.
                navigationHandler.handleNavigation(facesContext, null, "/restrict/home.faces");
 
                // Renders the error page and displays messages
                facesContext.renderResponse();
            } finally {
                // Remove the exception queue
                iterator.remove();
            }
        }
        // Handles the error
        getWrapped().handle();
    }
}
