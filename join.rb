#!/usr/bin/env ruby
# frozen_string_literal: true

# Run it with two arguments: before.txt after.txt

tests = {}
File.readlines(ARGV[0]).each do |ln|
  n, t = ln.split(' ')
  tests[n] = { before: t.to_f }
end
File.readlines(ARGV[1]).each do |ln|
  n, t = ln.split(' ')
  next unless tests[n]
  tests[n][:after] = t.to_f
end

tests.each do |n, h|
  next if h[:before] < 0.01
  sec = h[:before] - h[:after]
  diff = (sec) / h[:before]
  next if diff.abs < 0.1
  puts "#{n.split('#')[1]} #{format('%.1f', diff * 100)}% #{(sec * 1000).round}ms"
end
