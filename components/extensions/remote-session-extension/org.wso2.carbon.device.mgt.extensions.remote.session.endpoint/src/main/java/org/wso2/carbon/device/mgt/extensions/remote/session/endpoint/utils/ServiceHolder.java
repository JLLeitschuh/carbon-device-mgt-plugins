/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.device.mgt.extensions.remote.session.endpoint.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.device.mgt.extensions.remote.session.RemoteSessionManagementService;

/**
 * Class for store remote management service instances
 */
public class ServiceHolder {

    private static ServiceHolder instance;
    private static final Log log = LogFactory.getLog(ServiceHolder.class);

    private ServiceHolder() {
    }

    public synchronized static ServiceHolder getInstance() {
        if (instance == null) {
            instance = new ServiceHolder();
        }
        return instance;
    }

    public RemoteSessionManagementService getRemoteSessionManagementService() {
        PrivilegedCarbonContext ctx = PrivilegedCarbonContext.getThreadLocalCarbonContext();
        RemoteSessionManagementService RemoteSessionManagementService =
                (RemoteSessionManagementService) ctx.getOSGiService(RemoteSessionManagementService.class, null);
        if (RemoteSessionManagementService == null) {
            String msg = "Remote Session Management service has not initialized.";
            log.error(msg);
            throw new IllegalStateException(msg);
        }
        return RemoteSessionManagementService;
    }

}
