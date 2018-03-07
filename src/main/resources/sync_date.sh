#!/bin/expect
set timeout 30
spawn sudo ntpdate -u [lindex $argv 0]
expect "Password:"
send "xuning!\r"
interact