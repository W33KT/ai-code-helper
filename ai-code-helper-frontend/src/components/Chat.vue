<template>
  <el-container class="chat-layout">
    <el-header class="chat-header">
      <h3>AI Code Helper</h3>
    </el-header>
    <el-main class="chat-main">
      <el-scrollbar ref="scrollbarRef">
        <div class="chat-history">
          <div v-for="message in messages" :key="message.id" class="message-row" :class="{'user-row': message.isUser}">
            <el-avatar v-if="!message.isUser" :size="40" class="avatar">AI</el-avatar>
            <el-card class="message-card" :body-style="{ padding: '12px' }" :class="{ 'user-message': message.isUser, 'ai-message': !message.isUser }">
              <div v-if="message.isUser">
                <p>{{ message.text }}</p>
              </div>
              <div v-else v-html="marked(message.text)"></div>
            </el-card>
             <el-avatar v-if="message.isUser" :size="40" class="avatar">You</el-avatar>
          </div>
        </div>
      </el-scrollbar>
    </el-main>
    <el-footer class="chat-footer">
      <el-input
        v-model="newMessage"
        placeholder="Ask me anything..."
        @keyup.enter="sendMessage"
        size="large"
        class="chat-input"
      >
        <template #append>
          <el-button :icon="Promotion" @click="sendMessage" />
        </template>
      </el-input>
    </el-footer>
  </el-container>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue';
import { useEventSource } from '@vueuse/core';
import { marked } from 'marked';
import { Promotion } from '@element-plus/icons-vue'

const messages = ref([]);
const newMessage = ref('');
const memoryId = ref(null);
const scrollbarRef = ref();

onMounted(() => {
  memoryId.value = Date.now() % 2147483647;
});

const scrollToBottom = () => {
  nextTick(() => {
    scrollbarRef.value?.wrapRef.scrollTo({ top: scrollbarRef.value?.wrapRef.scrollHeight, behavior: 'smooth' });
  });
};

const sendMessage = () => {
  if (newMessage.value.trim() === '') return;

  const userMessage = { id: Date.now(), text: newMessage.value, isUser: true };
  messages.value.push(userMessage);
  scrollToBottom();

  const { eventSource, close } = useEventSource(`http://localhost:8081/api/ai/chat?memoryId=${memoryId.value}&userMessage=${newMessage.value}`);

  const aiMessagePlaceholder = { id: Date.now() + 1, text: '', isUser: false };
  messages.value.push(aiMessagePlaceholder);
  scrollToBottom();

  const reactiveAiMessage = messages.value[messages.value.length - 1];

  eventSource.value.onmessage = (event) => {
    if (event.data) {
      const token = event.data;
      const lastChar = reactiveAiMessage.text.slice(-1);
      const punctuation = ['.', ',', '!', '?', ':', ';', ')', ']', '}'];
      const markdownChars = ['*', '_', '`'];

      if (
        reactiveAiMessage.text.length > 0 &&
        !/\s/.test(lastChar) &&
        !punctuation.includes(token[0]) &&
        !markdownChars.includes(lastChar)
      ) {
        reactiveAiMessage.text += ' ';
      }
      reactiveAiMessage.text += token;
    } else {
      reactiveAiMessage.text += '\\n\\n';
    }
    scrollToBottom();
  };

  eventSource.value.onerror = () => {
    close();
  };

  newMessage.value = '';
};
</script>

<style>
/* Use global styles for layout as it's the main component */
.chat-layout {
  height: 100vh;
  max-width: 800px;
  margin: 0 auto;
  border: 1px solid var(--el-border-color-light);
  border-radius: 8px;
  overflow: hidden;
}

.chat-header {
  text-align: center;
  border-bottom: 1px solid var(--el-border-color-light);
  display: flex;
  align-items: center;
  justify-content: center;
}

.chat-main {
  padding: 0; /* Remove padding to let scrollbar take full space */
}

.chat-history {
  padding: 20px;
}

.message-row {
  display: flex;
  margin-bottom: 20px;
  align-items: center;
}

.user-row {
  justify-content: flex-end;
}

.avatar {
  flex-shrink: 0;
  margin: 0 10px;
}

.message-card {
  max-width: 70%;
  border-radius: 10px;
  border: none;
}

.user-message {
  background-color: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}

.ai-message {
  background-color: var(--el-color-info-light-9);
}

.chat-footer {
  border-top: 1px solid var(--el-border-color-light);
  padding: 10px 20px;
  display: flex;
  align-items: center;
}

.chat-input {
  width: 100%;
}

/* Style for rendered markdown */
.ai-message div {
  line-height: 1.6;
}
.ai-message div p {
  margin: 0 0 10px 0;
}
.ai-message div pre {
  background-color: #f4f4f5;
  padding: 10px;
  border-radius: 4px;
  overflow-x: auto;
}
.ai-message div code {
  font-family: 'Courier New', Courier, monospace;
}
</style>
