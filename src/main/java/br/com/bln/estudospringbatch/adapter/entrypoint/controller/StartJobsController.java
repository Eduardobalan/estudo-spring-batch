package br.com.bln.estudospringbatch.adapter.entrypoint.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class StartJobsController {

    @Autowired
    private List<Job> jobs;

    @Autowired
    @Qualifier("batch-repositorio")
    private JobRepository repository;

    @PostMapping("/startJobs/{jobName}/{jobId}")
    public void startJobs(@PathVariable("jobName") String jobName,
                          @PathVariable("jobId") String jobId) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        log.info("## Iniciando job via controller");

        JobParameters params = new JobParametersBuilder()
                .addString("jobId", jobId)
                .toJobParameters();

        for (Job job : jobs) {
            if (job.getName().equalsIgnoreCase(jobName)) {
                SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                jobLauncher.setJobRepository(repository);
                jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());

                jobLauncher.run(job, params);
            }
        }

        log.info("## Job via controller agendado com sucesso");
    }

}
