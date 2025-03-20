package com.nothing.modules.crawlers.leetcode

import com.nothing.helper.annotations.springcomponents.InjectableComponent
import com.nothing.modules.crawlers.api.SubmissionFetcher
import org.springframework.web.reactive.function.client.WebClient

@InjectableComponent class LCSubmissionFetcher implements SubmissionFetcher {
    public final WebClient lcUsWebClient
    public final WebClient lcCnWebClient

    @Override String fetchCode(String cId, String probId, String subId, List<String> info) { requestSub(cId, subId, info).code }

    def requestSub(String cId, String subId, List<String> info) {
        if (info[0] == 'US') {
            return lcUsWebClient.blockingGetWHeader("/submissions/$subId/",
                    'referer', "https://leetcode.com/contest/$cId/ranking/?region=global_v2", 10)
        } else {
            return lcCnWebClient.blockingGetWHeader("/submissions/$subId/",
                    'referer', "https://leetcode.com/", 10)
        }
    }
}
