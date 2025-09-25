#!/usr/bin/env ruby
# frozen_string_literal: true

# Run it with two arguments: before.txt after.txt

tests = {}
File.readlines(ARGV[0]).each do |ln|
  n, t = ln.split(' ')
  tests[n] = { before: t.to_i }
end
File.readlines(ARGV[1]).each do |ln|
  n, t = ln.split(' ')
  next unless tests[n]
  tests[n][:after] = t.to_i
end

gain = 0
total = 0

tests.each do |n, h|
  total += h[:before]
  ns = h[:before] - h[:after]
  next if ns < 1_000_000
  diff = ns.to_f / h[:before]
  next if diff.abs < 0.05
  gain += ns
  puts "#{n.split('#')[1]} #{h[:before]} #{h[:after]} #{ns} #{format('%.1f', diff * 100)}%"
end

puts "Total duration: #{(total.to_f / 1_000_000_000).round} seconds"
puts "Total gain: #{format('%.2f', gain.to_f / 1_000_000_000)} seconds (#{format('%.2f', 100.to_f * gain / total)}%)"
