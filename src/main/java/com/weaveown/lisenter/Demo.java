package com.weaveown.lisenter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangwei
 * @date 2022/1/13
 */
public class Demo {
    public static void main(String[] args) {
        final PluginContext pluginContext = new PluginContext();
        pluginContext.addPluginListener(new PluginListener() {
            @Override
            public String name() {
                return "aaa";
            }

            @Override
            public void listener(Object data) {
                System.out.println(data);
            }
        });

        pluginContext.publish(new PluginEvent("aaa", "aaa"));
    }


    public interface PluginListener {
        String name();

        void listener(Object data);
    }

    public static class PluginEvent {
        private static final long serialVersionUID = 7099057708183571937L;

        /**
         * System time when the event happened
         */
        private final long timestamp;
        private final String name;
        private final Object source;


        /**
         * Create a new ApplicationEvent.
         *
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public PluginEvent(String name, Object source) {
            this.name = name;
            this.source = source;
            this.timestamp = System.currentTimeMillis();
        }


        /**
         * Return the system time in milliseconds when the event happened.
         */
        public final long getTimestamp() {
            return this.timestamp;
        }
    }

    public static class PluginContext {
        private Map<String, PluginListener> pluginListeners = new LinkedHashMap<>();

        public void addPluginListener(PluginListener pluginListener) {
            pluginListeners.put(pluginListener.name(), pluginListener);
        }

        public void publish(PluginEvent pluginEvent) {
            pluginListeners.get(pluginEvent.name).listener(pluginEvent.source);
        }
    }
}
