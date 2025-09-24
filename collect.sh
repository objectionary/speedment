#!/bin/bash

# Just run this script after "mvn clean test" and you will
# get a list of all tests completed with their completion times.

set -e -o pipefail

tmp=$(mktemp)
t=$(mktemp)

while IFS= read -r xml; do
    xmllint -xpath '//testcase[not(ancestor::testcase)]' "${xml}" > "${tmp}"
    if [ -z "$(cat "${tmp}")" ]; then
        continue
    fi
    sed -i 's/^ .*$//g' "${tmp}"
    sed -i 's/^[^<].*$//g' "${tmp}"
    sed -i 's/">/"\/>/g' "${tmp}"
    while IFS= read -r ln; do
        echo "${ln}" > "${t}"
        if [ -z "$(cat "${t}" | xargs)" ]; then
            continue
        fi
        class=$(xmllint -xpath 'string(/testcase/@classname)' "${t}")
        test=$(xmllint -xpath 'string(/testcase/@name)' "${t}")
        time=$(xmllint -xpath 'string(/testcase/@time)' "${t}")
        echo "${class}#${test} ${time}"
    done < <(cat "${tmp}")
done < <(find . -name "TEST-*.xml")
