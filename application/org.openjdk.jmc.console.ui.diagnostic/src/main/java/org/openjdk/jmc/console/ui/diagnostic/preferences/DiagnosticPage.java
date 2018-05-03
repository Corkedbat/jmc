/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The contents of this file are subject to the terms of either the Universal Permissive License
 * v 1.0 as shown at http://oss.oracle.com/licenses/upl
 *
 * or the following license:
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.openjdk.jmc.console.ui.diagnostic.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.openjdk.jmc.console.ui.diagnostic.DiagnosticPlugin;
import org.openjdk.jmc.rjmx.services.IOperation.OperationImpact;
import org.openjdk.jmc.rjmx.ui.operations.OperationsLabelProvider;

/**
 * Preference page for console diagnostic commands tab.
 */
public class DiagnosticPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	/**
	 * Creates a new preference page for diagnostic commands.
	 */
	public DiagnosticPage() {
		super(GRID);
		setPreferenceStore(DiagnosticPlugin.getDefault().getPreferenceStore());
		setDescription(Messages.DiagnosticPage_LABEL_DIAGNOSTIC_COMMANDS_PREFERENCES_TEXT);

	}

	@Override
	protected void createFieldEditors() {
		addField(OperationImpact.IMPACT_MEDIUM);
		addField(OperationImpact.IMPACT_HIGH);
		addField(OperationImpact.IMPACT_UNKNOWN);
	}

	private void addField(OperationImpact impact) {
		String promptKey = PreferenceConstants.getPromptKey(impact);
		addField(new BooleanFieldEditor(promptKey, getPromptQuestion(impact), getFieldEditorParent()));
	}

	public static String getPromptQuestion(OperationImpact impact) {
		String impactName = OperationsLabelProvider.impactAsString(impact);
		return NLS.bind(Messages.DiagnosticPage_LABEL_ASK_BEFORE_EXECUTE_DIAGNOSTIC_COMMAND_LABEL, impactName);
	}

	@Override
	public void init(IWorkbench workbench) {
	}

}
