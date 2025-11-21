CREATE DATABASE IF NOT EXISTS ai_code_helper DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE ai_code_helper;

CREATE TABLE `chat_message_record` (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `memory_id` varchar(64) NOT NULL COMMENT '会话ID/用户ID',
                                       `role` varchar(64) DEFAULT NULL COMMENT '角色类型',
                                       `message_json` text NOT NULL COMMENT '消息JSON内容',
                                       `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       PRIMARY KEY (`id`),
                                       KEY `idx_memory_id` (`memory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI对话记忆表';