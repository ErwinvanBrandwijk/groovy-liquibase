//
// Groovy Liquibase ChangeLog
//
// Copyright (C) 2010 Tim Berglund
// http://augusttechgroup.com
// Littleton, CO
//
// Licensed under the Apache License 2.0
//

package com.augusttechgroup.liquibase.delegate

import org.junit.Test
import static org.junit.Assert.*

import liquibase.change.core.AddForeignKeyConstraintChange
import liquibase.change.core.DropForeignKeyConstraintChange
import liquibase.change.core.AddPrimaryKeyChange
import liquibase.change.core.DropPrimaryKeyChange


class ReferentialIntegrityRefactoringTests
  extends ChangeSetTests
{

  @Test
  void addForeignKeyConstraint() {
    buildChangeSet {
      addForeignKeyConstraint(constraintName: 'fk_monkey_emotion', baseTableName: 'monkey', baseTableSchemaName: 'base_schema', baseColumnNames: 'emotion_id', referencedTableName: 'emotions', referencedTableSchemaName: 'referenced_schema', referencedColumnNames: 'id', deferrable: true, initiallyDeferred: true, onDelete: 'CASCADE', onUpdate: 'CASCADE')
    }

    def changes = changeSet.changes
    assertNotNull changes
    assertEquals 1, changes.size()
    assertTrue changes[0] instanceof AddForeignKeyConstraintChange
    assertEquals 'fk_monkey_emotion', changes[0].constraintName
    assertEquals 'monkey', changes[0].baseTableName
    assertEquals 'base_schema', changes[0].baseTableSchemaName
    assertEquals 'emotion_id', changes[0].baseColumnNames
    assertEquals 'emotions', changes[0].referencedTableName
    assertEquals 'referenced_schema', changes[0].referencedTableSchemaName
    assertEquals 'id', changes[0].referencedColumnNames
    assertTrue changes[0].deferrable
    assertTrue changes[0].initiallyDeferred
    assertEquals 'CASCADE', changes[0].onDelete
    assertEquals 'CASCADE', changes[0].onUpdate    
  }



  @Test
  void addForeignKeyConstraintWithDeleteCascadeProperty() {
    buildChangeSet {
      addForeignKeyConstraint(constraintName: 'fk_monkey_emotion', baseTableName: 'monkey', baseTableSchemaName: 'base_schema', baseColumnNames: 'emotion_id', referencedTableName: 'emotions', referencedTableSchemaName: 'referenced_schema', referencedColumnNames: 'id', deferrable: true, initiallyDeferred: true, deleteCascade: true, onUpdate: 'CASCADE')
    }

    def changes = changeSet.changes
    assertNotNull changes
    assertEquals 1, changes.size()
    assertTrue changes[0] instanceof AddForeignKeyConstraintChange
    assertEquals 'fk_monkey_emotion', changes[0].constraintName
    assertEquals 'monkey', changes[0].baseTableName
    assertEquals 'base_schema', changes[0].baseTableSchemaName
    assertEquals 'emotion_id', changes[0].baseColumnNames
    assertEquals 'emotions', changes[0].referencedTableName
    assertEquals 'referenced_schema', changes[0].referencedTableSchemaName
    assertEquals 'id', changes[0].referencedColumnNames
    assertTrue changes[0].deferrable
    assertTrue changes[0].initiallyDeferred
    assertEquals 'CASCADE', changes[0].onDelete
    assertEquals 'CASCADE', changes[0].onUpdate
  }


  @Test
  void dropForeignKeyConstraint() {
    buildChangeSet {
      dropForeignKeyConstraint(constraintName: 'fk_monkey__emotion', baseTableName: 'monkey', baseTableSchemaName: 'schema')
    }

    def changes = changeSet.changes
    assertNotNull changes
    assertEquals 1, changes.size()
    assertTrue changes[0] instanceof DropForeignKeyConstraintChange
    assertEquals 'fk_monkey__emotion', changes[0].constraintName
    assertEquals 'monkey', changes[0].baseTableName
    assertEquals 'schema', changes[0].baseTableSchemaName
  }


  @Test
  void addPrimaryKey() {
    buildChangeSet {
      addPrimaryKey(tableName: 'monkey', schemaName: 'schema', columnNames: 'id', constraintName: 'pk_monkey', tablespace: 'tablespace')
    }

    def changes = changeSet.changes
    assertNotNull changes
    assertEquals 1, changes.size()
    assertTrue changes[0] instanceof AddPrimaryKeyChange
    assertEquals 'pk_monkey', changes[0].constraintName
    assertEquals 'monkey', changes[0].tableName
    assertEquals 'schema', changes[0].schemaName
    assertEquals 'tablespace', changes[0].tablespace
    assertEquals 'id', changes[0].columnNames
  }


  @Test
  void dropPrimaryKey() {
    buildChangeSet {
      dropPrimaryKey(tableName: 'monkey', schemaName: 'schema', constraintName: 'pk_monkey')
    }

    def changes = changeSet.changes
    assertNotNull changes
    assertEquals 1, changes.size()
    assertTrue changes[0] instanceof DropPrimaryKeyChange
    assertEquals 'pk_monkey', changes[0].constraintName
    assertEquals 'monkey', changes[0].tableName
    assertEquals 'schema', changes[0].schemaName
  }

}
