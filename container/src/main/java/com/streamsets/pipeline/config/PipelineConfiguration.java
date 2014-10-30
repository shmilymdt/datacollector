/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PipelineConfiguration {

  public enum OnError { DROP_RECORD, DROP_BATCH, STOP_PIPELINE }

  private UUID uuid = null;
  private List<StageConfiguration> stages;
  private Map<String, List<String>> issues;
  private OnError onError;

  @SuppressWarnings("unchecked")
  public PipelineConfiguration(
      @JsonProperty("uuid") UUID uuid,
      @JsonProperty("stages") List<StageConfiguration> stages,
      @JsonProperty("onError") OnError onError) {
    this.uuid = Preconditions.checkNotNull(uuid, "uuid cannot be null");
    this.stages = (stages != null) ? stages : Collections.EMPTY_LIST;
    issues = Collections.EMPTY_MAP;
    this.onError = Preconditions.checkNotNull(onError, "onError cannot be null");
  }

  public List<StageConfiguration> getStages() {
    return stages;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setIssues(Map<String, List<String>> issues) {
    this.issues = issues;
  }

  public Map<String, List<String>> getIssues() {
    return issues;
  }

  public void setValid(boolean dummy) {
    //NOP, just for jackson
  }

  public boolean isValid() {
    return issues == null || issues.isEmpty();
  }

  public OnError getOnError() {
    return onError;
  }

}
