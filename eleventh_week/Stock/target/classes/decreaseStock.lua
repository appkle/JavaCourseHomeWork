local curStock = redis.call("get", KEYS[1])
curStock = tonumber(curStock)
if curStock >= tonumber(ARGV[1]) then
   return redis.call("decrby", KEYS[1], ARGV[1])
else
   return -1
end