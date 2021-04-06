local key = KEYS[1]
local limit = tonumber(ARGS[1])
local current = tonumber(redis.call('get', key) or "0")
if current > limit then
    return 0
else
    redis.call('INCRBY', key, "1")
    redis.call('expire', key, "2")
    return 1
end