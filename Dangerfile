# Print reports for each test result
Dir.glob('vimeo-networking/build/test-results/test/*.xml') do |result|
    junit.parse result
    junit.report
end