# Print reports for each test result
Dir.glob('vimeo-networking/build/test-results/*.xml') do |result|
    junit.parse result
    junit.report
end