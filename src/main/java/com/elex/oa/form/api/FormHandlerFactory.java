package com.elex.oa.form.api;
import com.elex.oa.form.impl.formhandler.PcFormHandler;
import com.elex.oa.form.impl.formhandler.PrintFormHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Component
public class FormHandlerFactory {

    @Autowired
    private PcFormHandler pcFormHandler;

    @Autowired
    private PrintFormHandler printFormHandler;

    private Map<String, IFormHandler> handlerMap = new HashMap();

    public FormHandlerFactory() {
        this.handlerMap.put("pc",pcFormHandler);
        this.handlerMap.put("print",printFormHandler);
    }

    public void setHandlers(List<IFormHandler> list) {
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            IFormHandler handler = (IFormHandler)var2.next();
            this.handlerMap.put(handler.getType(), handler);
        }

    }

    public IFormHandler getByType(String type) {
        return (IFormHandler)this.handlerMap.get(type);
    }
}