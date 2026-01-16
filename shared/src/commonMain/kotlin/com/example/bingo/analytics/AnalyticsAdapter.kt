package com.example.bingo.analytics

class AnalyticsAdapter(
    private val track: (name: String, params: Map<String, Any>) -> Unit,
    private val setUserIdFn: (id: String) -> Unit,
    private val setUserPropertyFn: (key: String, value: String) -> Unit
) {
    
    /**
     * Track an analytics event
     * @param name Event name
     * @param params Event parameters
     */
    fun trackEvent(name: String, params: Map<String, Any> = emptyMap()) {
        track(name, params)
    }
    
    /**
     * Set user ID for analytics
     * @param id User identifier
     */
    fun setUserId(id: String) {
        setUserIdFn(id)
    }
    
    /**
     * Set user property for analytics
     * @param key Property key
     * @param value Property value
     */
    fun setUserProperty(key: String, value: String) {
        setUserPropertyFn(key, value)
    }
}
