package com.w33kt.aicodehelper.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

/**
 * @author tankaiwen
 */
public interface AICodeHelperService {
    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);
}
