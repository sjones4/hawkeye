package com.appscale.hawkeye.modules;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTaskHandlerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String queue_name = request.getParameter("queue");
        Queue queue;

        if (queue_name == null)
            queue = QueueFactory.getDefaultQueue();
        else
            queue = QueueFactory.getQueue(queue_name);

        String url = "/modules/create-entity";
        TaskOptions options = TaskOptions.Builder.withUrl(url).param("id", id).method(TaskOptions.Method.GET);
        queue.add(options);
    }
}
