package com.someco.action.executer;

import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleRepoActionExecuter extends ActionExecuterAbstractBase {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleRepoActionExecuter.class);

    public static final String PARAM_SIMPLE = "simpleParam";

    /**
     * The Alfresco Service Registry that gives access to all public content services in Alfresco.
     */
    private ServiceRegistry serviceRegistry;

    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
        paramList.add(new ParameterDefinitionImpl(
                PARAM_SIMPLE,
                DataTypeDefinition.TEXT,
                true,
                getParamDisplayLabel(PARAM_SIMPLE)));
    }

    @Override
    protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {
        // Get parameter values
        String simpleParam = (String) action.getParameterValue(PARAM_SIMPLE);

        LOG.info("Simple Repo Action called from scheduled Job, [" + PARAM_SIMPLE + "=" + simpleParam + "]");

        if (serviceRegistry.getNodeService().exists(actionedUponNodeRef) == true) {
            // The implementation of the Repo Action goes here...
            String nodeName = (String)serviceRegistry.getNodeService().getProperty(
                    actionedUponNodeRef, ContentModel.PROP_NAME);

            LOG.info("Simple Repo Action invoked on node [name=" + nodeName + "]");
        }
    }
}

